using Hl7.Fhir.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;

namespace Hl7.Fhir.Model
{
    public class ManagedEntryList : Collection<BundleEntry>
    {
        public Bundle Parent { get; set; }

        public ManagedEntryList(Bundle parent)
        {
            Parent = parent;
        }

        protected override void InsertItem(int index, BundleEntry item)
        {
            if( item != null ) item.Parent = this.Parent;
            
            base.InsertItem(index, item);
        }

        protected override void ClearItems()
        {
            foreach (var item in this.Items) item.Parent = null;
            base.ClearItems();
        }


        protected override void RemoveItem(int index)
        {
            Items[index].Parent = null;
            base.RemoveItem(index);
        }

        protected override void SetItem(int index, BundleEntry item)
        {
            if( item != null ) item.Parent = this.Parent;

            base.SetItem(index, item);
        }

        public void AddRange(IEnumerable<BundleEntry> entries)
        {
            foreach(var entry in entries) this.Add(entry);
        }
        
    }
}
